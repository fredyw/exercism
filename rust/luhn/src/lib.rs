/// Check a Luhn checksum.
pub fn is_valid(code: &str) -> bool {
    let code = code.replace(" ", "");
    if code.len() <= 1 {
        return false;
    }
    if !code.chars().all(|c| c.is_digit(10)) {
        return false;
    }
    let sum: u32 = code
        .chars()
        .rev()
        .filter_map(|c| c.to_digit(10))
        .enumerate()
        .map(|(i, n)| {
            if i % 2 != 0 {
                let double = n * 2;
                if double > 9 {
                    double - 9
                } else {
                    double
                }
            } else {
                n
            }
        })
        .sum();
    sum % 10 == 0
}
