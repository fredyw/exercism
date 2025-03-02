pub fn primes_up_to(upper_bound: u64) -> Vec<u64> {
    let mut primes = vec![];
    let mut visited: Vec<bool> = vec![false; upper_bound as usize + 1];
    for i in 2..=upper_bound {
        if visited[i as usize] {
            continue;
        }
        primes.push(i);
        let mut j = i;
        while j <= upper_bound {
            visited[j as usize] = true;
            j += i;
        }
    }
    primes
}
