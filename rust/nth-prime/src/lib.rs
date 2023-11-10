pub fn nth(n: u32) -> u32 {
    fn is_prime(n: u32) -> bool {
        if n <= 1 {
            return false;
        }
        (2..=(n as f32).sqrt().ceil() as u32)
            .filter(|&num| num != n)
            .all(|num| n % num != 0)
    }

    let mut i = 0;
    let mut num = 2;
    while i <= n {
        while !is_prime(num) {
            num += 1;
        }
        num += 1;
        i += 1;
    }
    num - 1
}
