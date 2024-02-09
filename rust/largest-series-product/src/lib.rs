use crate::Error::{InvalidDigit, SpanTooLong};

#[derive(Debug, PartialEq, Eq)]
pub enum Error {
    SpanTooLong,
    InvalidDigit(char),
}

pub fn lsp(string_digits: &str, span: usize) -> Result<u64, Error> {
    if string_digits.len() < span {
        return Err(SpanTooLong);
    }
    if let Some(c) = string_digits.chars().find(|c| !c.is_numeric()) {
        return Err(InvalidDigit(c));
    }
    todo!("largest series product of a span of {span} digits in {string_digits}");
}
