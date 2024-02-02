use std::collections::HashSet;

pub fn check(candidate: &str) -> bool {
    let s: String = candidate.to_lowercase().chars().filter(|c| c.is_alphabetic()).collect();
    s.chars().collect::<HashSet<char>>().len() == s.len()
}
