pub fn is_armstrong_number(num: u32) -> bool {
    let string = format!("{}", num);
    let sum = string
        .chars()
        .into_iter()
        .map(|c| (c.to_digit(10).unwrap() as u64).pow(string.len() as u32))
        .sum::<u64>();
    sum == num as u64
}
