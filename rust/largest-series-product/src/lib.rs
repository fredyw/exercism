#[derive(Debug, PartialEq, Eq)]
pub enum Error {
    SpanTooLong,
    InvalidDigit(char),
}

pub fn lsp(string_digits: &str, span: usize) -> Result<u64, Error> {
    if string_digits.len() < span {
        return Err(Error::SpanTooLong);
    }
    if let Some(c) = string_digits.chars().find(|c| !c.is_numeric()) {
        return Err(Error::InvalidDigit(c));
    }
    Ok(string_digits
        .as_bytes()
        .iter()
        .as_slice()
        .windows(span)
        .map(|slice| {
            slice
                .iter()
                .fold(1u64, |acc, b| acc * (b - '0' as u8) as u64)
        })
        .max()
        .unwrap())
}
