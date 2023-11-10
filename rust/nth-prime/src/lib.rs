pub fn nth(n: u32) -> u32 {
    fn is_prime(n: u32) -> bool {
        if n <= 1 {
            return false;
        }
        (2..=(n as f32).sqrt() as u32).all(|num| n % num != 0)
    }

    (2..).filter(|&x| is_prime(x)).nth(n as usize).unwrap()
}
