use std::collections::HashSet;

pub fn find(sum: u32) -> HashSet<[u32; 3]> {
    let mut triplets = HashSet::new();
    if sum % 2 != 0 {
        return triplets;
    }
    for a in 1..sum / 3 {
        // b = (sum^2 - 2 *sum * a) / (2 * sum - 2a)
        let numerator = sum.pow(2) - 2 * sum * a;
        let denominator = 2 * sum - 2 * a;
        if numerator % denominator == 0 {
            let b = numerator / denominator;
            if b > a {
                let c = sum - a - b;
                triplets.insert([a, b, c]);
            }
        }
    }
    triplets
}
