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
#[derive(Debug)]
pub struct Flags {
    show_line_number: bool,
    file_name_only: bool,
    case_insensitive: bool,
    invert: bool,
    match_entire_line: bool,
}

impl Flags {
    pub fn new(flags: &[&str]) -> Self {
        Self {
            show_line_number: flags.contains(&"-n"),
            file_name_only: flags.contains(&"-l"),
            case_insensitive: flags.contains(&"-i"),
            invert: flags.contains(&"-v"),
            match_entire_line: flags.contains(&"-x"),
        }
    }
}

pub fn grep(pattern: &str, flags: &Flags, files: &[&str]) -> Result<Vec<String>, Error> {
    let mut result: Vec<String> = vec![];
    let has_multiple_files = files.len() > 1;
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
            let maybe_invert_search = |m: bool| -> bool { if flags.invert { !m } else { m } };
            if flags.file_name_only {
                if maybe_invert_search(has_match) {
                    result.push(file.to_string());
                    break;
                }
            } else {
                if maybe_invert_search(has_match) {
                    result.push(build_output(
                        flags.show_line_number,
                        has_multiple_files,
                        file,
                        i,
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
    has_multiple_files: bool,
    file: &str,
    index: usize,
    line: String,
) -> String {
    format!(
        "{}{}{}",
        if has_multiple_files {
            format!("{}:", file)
        } else {
            "".to_string()
        },
        if show_line_number {
            format!("{}:", index + 1)
        } else {
            "".to_string()
        },
        line
    )
}
