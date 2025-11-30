use rand::Rng;
use rand::distr::Uniform;

pub fn encode(key: &str, s: &str) -> Option<String> {
    if !is_valid(key) || !is_valid(s) {
        return None;
    }
    let key = key.as_bytes();
    Some(
        s.as_bytes()
            .iter()
            .enumerate()
            .map(|(i, b)| {
                let p = *b - b'a';
                let k = key[i % key.len()] - b'a';
                (b'a' + (p + k) % 26) as char
            })
            .collect::<String>(),
    )
}

pub fn decode(key: &str, s: &str) -> Option<String> {
    if !is_valid(key) || !is_valid(s) {
        return None;
    }
    let key = key.as_bytes();
    Some(
        s.as_bytes()
            .iter()
            .enumerate()
            .map(|(i, b)| {
                let e = (*b - b'a') as i32;
                let k = (key[i % key.len()] - b'a') as i32;
                (b'a' as i32 + (e - k + 26) % 26) as u8 as char
            })
            .collect::<String>(),
    )
}

pub fn encode_random(s: &str) -> (String, String) {
    let rng = rand::rng();
    let char_range = Uniform::new_inclusive(b'a', b'z').unwrap();
    let key: String = rng
        .sample_iter(char_range)
        .take(100)
        .map(|c| c as char)
        .collect();
    let encoded = encode(&key, &s).unwrap();
    (key, encoded)
}

fn is_valid(s: &str) -> bool {
    !s.is_empty() && s.chars().all(|c| c.is_ascii_lowercase())
}
