pub fn number(user_number: &str) -> Option<String> {
    let phone_number = user_number.replace(|c: char| !c.is_numeric(), "");
    if phone_number.starts_with("0") {
        None
    } else if phone_number.len() == 11 {
        Some(
            phone_number.chars().collect::<Vec<char>>()[1..]
                .iter()
                .collect::<String>(),
        )
    } else if phone_number.len() == 10 {
        Some(phone_number)
    } else {
        None
    }
}
