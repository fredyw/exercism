const SCALES: &[(u64, &str)] = &[
    (1_000_000_000_000_000_000, "quintillion"),
    (1_000_000_000_000_000, "quadrillion"),
    (1_000_000_000_000, "trillion"),
    (1_000_000_000, "billion"),
    (1_000_000, "million"),
    (1_000, "thousand"),
];

pub fn encode(mut n: u64) -> String {
    if n == 0 {
        return say(0).to_string();
    }
    let mut parts: Vec<String> = Vec::new();
    for &(value, name) in SCALES {
        let chunk = n / value;
        if chunk > 0 {
            parts.push(encode_under_1000(chunk));
            parts.push(name.to_string());
            n %= value;
        }
    }
    if n > 0 {
        parts.push(encode_under_1000(n));
    }
    parts.join(" ")
}

fn encode_under_1000(n: u64) -> String {
    let mut parts: Vec<String> = Vec::new();
    let hundreds = n / 100;
    if hundreds > 0 {
        parts.push(say(hundreds).to_string());
        parts.push(say(100).to_string());
    }
    let tens_and_ones = n % 100;
    if tens_and_ones > 0 {
        if tens_and_ones <= 20 {
            parts.push(say(tens_and_ones).to_string());
        } else {
            let tens = (tens_and_ones / 10) * 10;
            let ones = tens_and_ones % 10;
            if ones == 0 {
                parts.push(say(tens).to_string());
            } else {
                parts.push(format!("{}-{}", say(tens), say(ones)));
            }
        }
    }
    parts.join(" ")
}

fn say(n: u64) -> String {
    match n {
        0 => String::from("zero"),
        1 => String::from("one"),
        2 => String::from("two"),
        3 => String::from("three"),
        4 => String::from("four"),
        5 => String::from("five"),
        6 => String::from("six"),
        7 => String::from("seven"),
        8 => String::from("eight"),
        9 => String::from("nine"),
        10 => String::from("ten"),
        11 => String::from("eleven"),
        12 => String::from("twelve"),
        13 => String::from("thirteen"),
        14 => String::from("fourteen"),
        15 => String::from("fifteen"),
        16 => String::from("sixteen"),
        17 => String::from("seventeen"),
        18 => String::from("eighteen"),
        19 => String::from("nineteen"),
        20 => String::from("twenty"),
        30 => String::from("thirty"),
        40 => String::from("forty"),
        50 => String::from("fifty"),
        60 => String::from("sixty"),
        70 => String::from("seventy"),
        80 => String::from("eighty"),
        90 => String::from("ninety"),
        100 => String::from("hundred"),
        _ => panic!("not supported: {}", n),
    }
}
