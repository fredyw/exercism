use anyhow::Error;
use std::fs::File;
use std::io;
use std::io::BufRead;

/// While using `&[&str]` to handle flags is convenient for exercise purposes,
/// and resembles the output of [`std::env::args`], in real-world projects it is
/// both more convenient and more idiomatic to contain runtime configuration in
/// a dedicated struct. Therefore, we suggest that you do so in this exercise.
///
/// [`std::env::args`]: https://doc.rust-lang.org/std/env/fn.args.html
#[derive(Debug, Default)]
pub struct Flags {
    show_line_number: bool,
    file_name_only: bool,
    case_insensitive: bool,
    invert_search: bool,
    match_entire_line: bool,
}

impl Flags {
    pub fn new(flags: &[&str]) -> Self {
        let mut f = Self::default();
        for flag in flags {
            match *flag {
                "-n" => f.show_line_number = true,
                "-l" => f.file_name_only = true,
                "-i" => f.case_insensitive = true,
                "-v" => f.invert_search = true,
                "-x" => f.match_entire_line = true,
                _ => (),
            }
        }
        f
    }
}

pub fn grep(pattern: &str, flags: &Flags, files: &[&str]) -> Result<Vec<String>, Error> {
    let mut result: Vec<String> = vec![];
    let show_filename = files.len() > 1;
    for file in files {
        for (i, line) in io::BufReader::new(File::open(file)?).lines().enumerate() {
            let pattern = if flags.case_insensitive {
                pattern.to_lowercase()
            } else {
                pattern.to_owned()
            };
            let original_line = line?;
            let line = if flags.case_insensitive {
                original_line.to_lowercase()
            } else {
                original_line.clone()
            };
            let has_match = if flags.match_entire_line {
                line == pattern
            } else {
                line.contains(&pattern)
            };
            let has_match = if flags.invert_search {
                !has_match
            } else {
                has_match
            };
            if flags.file_name_only {
                if has_match {
                    result.push(file.to_string());
                    break;
                }
            } else {
                if has_match {
                    result.push(build_output(
                        flags.show_line_number,
                        show_filename,
                        file,
                        i + 1,
                        original_line,
                    ));
                }
            }
        }
    }
    Ok(result)
}

fn build_output(
    show_line_number: bool,
    show_file_name: bool,
    file: &str,
    line_number: usize,
    line: String,
) -> String {
    match (show_file_name, show_line_number) {
        (false, false) => line,
        (true, false) => format!("{}:{}", file, line),
        (false, true) => format!("{}:{}", line_number, line),
        (true, true) => format!("{}:{}:{}", file, line_number, line),
    }
}
