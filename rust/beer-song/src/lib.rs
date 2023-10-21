pub fn verse(n: u32) -> String {
    fn n_bottles(n: u32) -> String {
        if n == 0 {
            "no more bottles".to_string()
        } else if n == 1 {
            format!("{} bottle", n)
        } else {
            format!("{} bottles", n)
        }
    }
    if n > 0 {
        format!(
            "{} of beer on the wall, {} of beer.\n\
            Take {} down and pass it around, {} of beer on the wall.\n",
            n_bottles(n),
            n_bottles(n),
            if n == 1 { "it" } else { "one" },
            n_bottles(n - 1),
        )
    } else {
        format!(
            "No more bottles of beer on the wall, no more bottles of beer.\n\
            Go to the store and buy some more, 99 bottles of beer on the wall.\n",
        )
    }
}

pub fn sing(start: u32, end: u32) -> String {
    (end..=start)
        .rev()
        .map(|n| verse(n))
        .collect::<Vec<_>>()
        .join("\n")
}
