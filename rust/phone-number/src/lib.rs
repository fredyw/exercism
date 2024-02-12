pub fn number(user_number: &str) -> Option<String> {
    let phone_number = user_number.replace(|c: char| !c.is_numeric(), "");
    if phone_number.len() == 11 {
        let country_code = &phone_number[0..1];
        let phone_number = &phone_number[1..];
        if country_code != "1" {
            None
        } else {
            get_number(phone_number)
        }
    } else if phone_number.len() == 10 {
        get_number(&phone_number)
    } else {
        None
    }
}

fn get_number(phone_number: &str) -> Option<String> {
    let area_code = &phone_number[0..3];
    let exchange_code = &phone_number[3..];
    if area_code.starts_with(['0', '1']) || exchange_code.starts_with(['0', '1']) {
        None
    } else {
        Some(phone_number.to_string())
    }
}
