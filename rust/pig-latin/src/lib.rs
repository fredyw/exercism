use regex::Regex;

pub fn translate(input: &str) -> String {
    let vowel = Regex::new(r"((?:[aeiou]|yr|xr|yt).*)").unwrap();
    let consonant = Regex::new(r"(thr?|sch|ch|s?qu|rh|[bcdfghjklmnpqrstvwxyz])(.*)").unwrap();
    let words: Vec<&str> = input.split(" ").collect();
    for word in words.into_iter() {
        for (_, [first]) in vowel.captures_iter(word).map(|c| c.extract()) {
            return format!("{}ay", first);
        }
        for (_, [first, second]) in consonant.captures_iter(word).map(|c| c.extract()) {
            // let repl = format!("{}{}ay", second, first);
            // return consonant.replace(word, repl).to_string();
            return format!("{}{}ay", second, first);
        }
    }
    input.to_string()
}
