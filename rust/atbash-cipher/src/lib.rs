use std::collections::HashMap;

/// "Encipher" with the Atbash cipher.
pub fn encode(plain: &str) -> String {
    let map: HashMap<char, char> = "abcdefghijklmnopqrstuvwxyz"
        .chars()
        .zip("zyxwvutsrqponmlkjihgfedcba".chars())
        .collect();
    plain
        .to_ascii_lowercase()
        .chars()
        .filter(|c| c.is_ascii_alphanumeric())
        .map(|c| *map.get(&c).unwrap_or(&c))
        .collect::<Vec<char>>()
        .chunks(5)
        .map(|chunk| chunk.iter().collect::<String>())
        .collect::<Vec<String>>()
        .join(" ")
}

/// "Decipher" with the Atbash cipher.
pub fn decode(cipher: &str) -> String {
    let map: HashMap<char, char> = "zyxwvutsrqponmlkjihgfedcba"
        .chars()
        .zip("abcdefghijklmnopqrstuvwxyz".chars())
        .collect();
    cipher
        .to_ascii_lowercase()
        .chars()
        .filter(|c| c.is_ascii_alphanumeric())
        .map(|c| *map.get(&c).unwrap_or(&c))
        .collect()
}
