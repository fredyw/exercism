use std::collections::BTreeSet;

/// `Palindrome` is a newtype which only exists when the contained value is a palindrome number in base ten.
///
/// A struct with a single field which is used to constrain behavior like this is called a "newtype", and its use is
/// often referred to as the "newtype pattern". This is a fairly common pattern in Rust.
#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub struct Palindrome(u64);

impl Palindrome {
    /// Create a `Palindrome` only if `value` is in fact a palindrome when represented in base ten. Otherwise, `None`.
    pub fn new(value: u64) -> Option<Palindrome> {
        let s = format!("{}", value);
        let bytes = s.as_bytes();
        let mut left = 0;
        let mut right = bytes.len() - 1;
        while left < right {
            if bytes[left] != bytes[right] {
                return None;
            }
            left += 1;
            right -= 1;
        }
        Some(Palindrome { 0: value })
    }

    /// Get the value of this palindrome.
    pub fn into_inner(self) -> u64 {
        self.0
    }
}

pub fn palindrome_products(min: u64, max: u64) -> Option<(Palindrome, Palindrome)> {
    let mut tree: BTreeSet<u64> = BTreeSet::new();
    for i in min..=max {
        for j in min..=max {
            if let Some(p) = Palindrome::new(i * j) {
                tree.insert(p.into_inner());
            }
        }
    }

    if tree.is_empty() {
        None
    } else {
        Some((
            Palindrome::new(*tree.iter().min().unwrap()).unwrap(),
            Palindrome::new(*tree.iter().max().unwrap()).unwrap(),
        ))
    }
}
