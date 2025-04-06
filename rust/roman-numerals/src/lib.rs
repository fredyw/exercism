use std::collections::HashMap;
use std::fmt::{Display, Formatter, Result};

pub struct Roman {
    num: u32,
    map: HashMap<u32, &'static str>,
}

impl Display for Roman {
    fn fmt(&self, f: &mut Formatter<'_>) -> Result {
        let mut v: Vec<String> = Vec::new();
        let mut num = self.num;
        let mut x = 1;
        while num > 0 {
            let n = num % 10;
            let s: String = match n {
                1 => (*self.map.get(&(1 * x)).unwrap()).to_owned(),
                2 => self.map.get(&(1 * x)).unwrap().repeat(2),
                3 => self.map.get(&(1 * x)).unwrap().repeat(3),
                4 => format!(
                    "{}{}",
                    self.map.get(&(1 * x)).unwrap(),
                    self.map.get(&(5 * x)).unwrap()
                ),
                5 => (*self.map.get(&(5 * x)).unwrap()).to_owned(),
                6 => format!(
                    "{}{}",
                    self.map.get(&(5 * x)).unwrap(),
                    self.map.get(&(1 * x)).unwrap()
                ),
                7 => format!(
                    "{}{}",
                    self.map.get(&(5 * x)).unwrap(),
                    self.map.get(&(1 * x)).unwrap().repeat(2)
                ),
                8 => format!(
                    "{}{}",
                    self.map.get(&(5 * x)).unwrap(),
                    self.map.get(&(1 * x)).unwrap().repeat(3)
                ),
                9 => format!(
                    "{}{}",
                    self.map.get(&(1 * x)).unwrap(),
                    self.map.get(&(10 * x)).unwrap()
                ),
                _ => "".to_owned(),
            };
            v.push(s);
            num /= 10;
            x *= 10;
        }
        write!(f, "{}", v.into_iter().rev().collect::<String>())
    }
}

impl From<u32> for Roman {
    fn from(num: u32) -> Self {
        let mut map: HashMap<u32, &str> = HashMap::new();
        map.insert(1, "I");
        map.insert(5, "V");
        map.insert(10, "X");
        map.insert(50, "L");
        map.insert(100, "C");
        map.insert(500, "D");
        map.insert(1000, "M");
        Self { num, map }
    }
}
