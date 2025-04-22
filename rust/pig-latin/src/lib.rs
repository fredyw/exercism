use regex::Regex;

pub fn translate(input: &str) -> String {
    let vowel = Regex::new(r"(^(?:[aeiou]|yr|xr|yt).*)$").unwrap();
    let consonant = Regex::new(r"^(thr?|sch|ch|s?qu|rh|[bcdfghjklmnpqrstvwxyz])(.*)$").unwrap();
    input
        .split(" ")
        .collect::<Vec<&str>>()
        .into_iter()
        .map(|word| {
            let s1: String = vowel
                .captures_iter(word)
                .map(|c| c.extract())
                .map(|(_, [first])| format!("{}ay", first))
                .collect();
            let s2: String = consonant
                .captures_iter(word)
                .map(|c| c.extract())
                .map(|(_, [first, second])| format!("{}{}ay", second, first))
                .collect();
            if !s1.is_empty() {
                s1
            } else if !s2.is_empty() {
                s2
            } else {
                word.to_string()
            }
        })
        .collect::<Vec<String>>()
        .join(" ")
}
