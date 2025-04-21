use crate::Classification::{Abundant, Deficient, Perfect};
use std::cmp::Ordering;

#[derive(Debug, PartialEq, Eq)]
pub enum Classification {
    Abundant,
    Perfect,
    Deficient,
}

pub fn classify(num: u64) -> Option<Classification> {
    if num == 0 {
        return None;
    }
    let sum: u64 = (1..=num.isqrt())
        .filter(|n| num % n == 0)
        .flat_map(|n| {
            if n == num / n {
                vec![n]
            } else {
                vec![n, num / n]
            }
        })
        .sum();
    match (sum - num).cmp(&num) {
        Ordering::Less => Some(Deficient),
        Ordering::Equal => Some(Perfect),
        Ordering::Greater => Some(Abundant),
    }
}
