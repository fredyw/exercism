use std::collections::HashSet;

pub fn anagrams_for<'a>(word: &str, possible_anagrams: &[&'a str]) -> HashSet<&'a str> {
    let lowercase_word = word.to_lowercase();
    let sorted_word: String = sort(word);
    possible_anagrams
        .iter()
        .filter(|w| {
            let lowercase_anagram = w.to_lowercase();
            let sorted_anagram = sort(w);
            lowercase_word != lowercase_anagram && sorted_word == sorted_anagram
        })
        .cloned()
        .collect()
}

fn sort(word: &str) -> String {
    let mut chars: Vec<char> = word.to_lowercase().chars().collect();
    chars.sort_unstable();
    chars.into_iter().collect()
}
