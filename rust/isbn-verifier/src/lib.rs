/// Determines whether the supplied string is a valid ISBN number
pub fn is_valid_isbn(isbn: &str) -> bool {
    let isbn = isbn.replace("-", "");
    if isbn.len() != 10 {
        return false;
    }
    match isbn
        .chars()
        .enumerate()
        .try_fold(0, |acc, (i, c)| -> Result<u32, ()> {
            if i == 9 && c == 'X' {
                Ok(acc + ((10 - i as u32) * 10))
            } else if c.is_numeric() {
                Ok(acc + (10 - i as u32) * c.to_digit(10).unwrap())
            } else {
                Err(())
            }
        }) {
        Ok(s) => s % 11 == 0,
        Err(_) => false,
    }
}
