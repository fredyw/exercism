pub fn recite(start_bottles: u32, take_down: u32) -> String {
    (start_bottles - take_down + 1..=start_bottles)
        .rev()
        .into_iter()
        .map(|n| generate_verse(n))
        .collect::<Vec<_>>()
        .join("\n\n")
}

fn to_word(n: u32) -> String {
    match n {
        1 => "One".to_string(),
        2 => "Two".to_string(),
        3 => "Three".to_string(),
        4 => "Four".to_string(),
        5 => "Five".to_string(),
        6 => "Six".to_string(),
        7 => "Seven".to_string(),
        8 => "Eight".to_string(),
        9 => "Nine".to_string(),
        10 => "Ten".to_string(),
        _ => "No".to_string(),
    }
}

fn bottle(n: u32) -> String {
    if n == 1 {
        "bottle".to_string()
    } else {
        "bottles".to_string()
    }
}

fn generate_verse(n: u32) -> String {
    format!(
        "{} green {} hanging on the wall,\n\
        {} green {} hanging on the wall,\n\
        And if one green bottle should accidentally fall,\n\
        There'll be {} green {} hanging on the wall.",
        to_word(n),
        bottle(n),
        to_word(n),
        bottle(n),
        to_word(n - 1).to_lowercase(),
        bottle(n - 1),
    )
}
