pub fn factors(n: u64) -> Vec<u64> {
    let mut prime_factors = vec![];
    let mut n = n;
    while n > 1 {
        for i in 2..=n {
            if n % i == 0 {
                prime_factors.push(i);
                n /= i;
                break;
            }
        }
    }
    prime_factors
}
