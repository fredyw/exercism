use std::fmt::{Display, Formatter};

#[derive(Debug, PartialEq, Eq)]
pub struct Clock {
    hours: i32,
    minutes: i32,
}

impl Clock {
    pub fn new(hours: i32, minutes: i32) -> Self {
        let m = minutes % 60;
        let h = (hours + (minutes / 60) + if m < 0 { -1 } else { 0 }) % 24;
        Clock {
            hours: if h < 0 { 24 + h } else { h },
            minutes: if m < 0 { 60 + m } else { m },
        }
    }

    pub fn add_minutes(&self, minutes: i32) -> Self {
        Self::new(self.hours, self.minutes + minutes)
    }
}

impl Display for Clock {
    fn fmt(&self, f: &mut Formatter<'_>) -> std::fmt::Result {
        write!(f, "{:02}:{:02}", self.hours, self.minutes)
    }
}
