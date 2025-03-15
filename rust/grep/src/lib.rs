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
    prepend_line_number: bool,
    output_file_name: bool,
    case_insensitive_search: bool,
    invert_search: bool,
    match_entire_line: bool,
}

impl Flags {
    pub fn new(flags: &[&str]) -> Self {
        Self {
            prepend_line_number: flags.contains(&"-n"),
            output_file_name: flags.contains(&"-l"),
            case_insensitive_search: flags.contains(&"-i"),
            invert_search: flags.contains(&"-v"),
            match_entire_line: flags.contains(&"-x"),
        }
    }
}

pub fn grep(pattern: &str, flags: &Flags, files: &[&str]) -> Result<Vec<String>, Error> {
    let mut result: Vec<String> = vec![];
    let has_multiple_files = files.len() > 1;
    for file in files {
        for (i, line) in io::BufReader::new(File::open(file)?).lines().enumerate() {
            let pattern = if flags.case_insensitive_search {
                pattern.to_lowercase()
            } else {
                pattern.to_owned()
            };
            let line = line?;
            let maybe_lowercase_line = if flags.case_insensitive_search {
                line.to_lowercase()
            } else {
                line.clone()
            };
            let may_invert_search = |m: bool| -> bool { if flags.invert_search { !m } else { m } };
            if flags.output_file_name {
                if may_invert_search(maybe_lowercase_line.contains(&pattern)) {
                    result.push(file.to_string());
                    break;
                }
            } else if flags.match_entire_line {
                if may_invert_search(maybe_lowercase_line == pattern) {
                    result.push(build_output(
                        flags.prepend_line_number,
                        has_multiple_files,
                        file,
                        i,
                        line,
                    ));
                }
            } else {
                if may_invert_search(maybe_lowercase_line.contains(&pattern)) {
                    result.push(build_output(
                        flags.prepend_line_number,
                        has_multiple_files,
                        file,
                        i,
                        line,
                    ));
                }
            }
        }
    }
    Ok(result)
}

fn build_output(
    prepend_line_number: bool,
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
        if prepend_line_number {
            format!("{}:", index + 1)
        } else {
            "".to_string()
        },
        line
    )
}
