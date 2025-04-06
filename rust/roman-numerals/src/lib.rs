use std::fmt::{Display, Formatter, Result};

pub struct Roman(u32);

impl Display for Roman {
    fn fmt(&self, _f: &mut Formatter<'_>) -> Result {
        todo!("Return a roman-numeral string representation of the Roman object");
    }
}

impl From<u32> for Roman {
    fn from(num: u32) -> Self {
        Self(num)
    }
}
