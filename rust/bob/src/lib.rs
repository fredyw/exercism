pub fn reply(message: &str) -> &str {
    let has_alphabet = message.chars().any(|c| c.is_alphabetic());
    match message.trim() {
        m if has_alphabet && m.to_uppercase() == message && m.ends_with("?") => {
            "Calm down, I know what I'm doing!"
        }
        m if has_alphabet && m.to_uppercase() == message => "Whoa, chill out!",
        m if m.ends_with("?") => "Sure.",
        m if m.is_empty() => "Fine. Be that way!",
        _ => "Whatever.",
    }
}
