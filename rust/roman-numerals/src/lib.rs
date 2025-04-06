use std::fmt::{Display, Formatter, Result};

pub struct Roman(u32);

impl Display for Roman {
    fn fmt(&self, f: &mut Formatter<'_>) -> Result {
        let mut s = String::new();
        let mut num = self.0;
        let mut x = 1;
        while num > 0 {
            let n = x * (num % 10);
            match n {
                1..5 => todo!(),
                5..10 => todo!(),
                10..50 => todo!(),
                50..100 => todo!(),
                100..500 => todo!(),
                _ => todo!(),
            }
            num /= 10;
            x *= 10;
        }
        write!(f, "{}", s.chars().rev().collect::<String>())
    }
}

impl From<u32> for Roman {
    fn from(num: u32) -> Self {
        Self(num)
    }
}
