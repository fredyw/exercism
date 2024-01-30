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
    if source.is_empty() {
        return "".to_string();
    }
    todo!("Return the run-length decoding of {source}.");
}
