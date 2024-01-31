extern crate core;

pub fn encode(source: &str) -> String {
    if source.is_empty() {
        return "".to_string();
    }
    let mut encoded = String::new();
    let chars: Vec<char> = source.chars().collect();
    let mut i = 0;
    let mut count = 1;
    while i < chars.len() - 1 {
        if chars[i] != chars[i + 1] {
            if count > 1 {
                encoded.push_str(&count.to_string());
            }
            encoded.push(chars[i]);
            count = 1;
        } else {
            count += 1;
        }
        i += 1;
    }
    if count > 1 {
        encoded.push_str(&count.to_string());
    }
    encoded.push(chars[i]);
    encoded
}

pub fn decode(source: &str) -> String {
    let mut decoded = String::new();
    let mut num = String::new();
    for char in source.chars() {
        if char.is_numeric() {
            num.push(char);
        } else {
            decoded.push_str(&char.to_string().repeat(num.parse::<usize>().unwrap_or(1)));
            num.clear();
        }
    }
    decoded
}
