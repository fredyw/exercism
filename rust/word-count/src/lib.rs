use std::collections::HashMap;

/// Count occurrences of words.
pub fn word_count(words: &str) -> HashMap<String, u32> {
    let mut map: HashMap<String, u32> = HashMap::new();
    words
        .to_lowercase()
        .split(|c: char| c.is_whitespace() || c == ',')
        .map(|word| {
            word.trim_matches(|c: char| !c.is_ascii_alphanumeric())
                .to_string()
        })
        .filter(|s| !s.is_empty())
        .fold(&mut map, |acc, word| {
            *acc.entry(word).or_insert(0) += 1;
            acc
        });
    map
}
