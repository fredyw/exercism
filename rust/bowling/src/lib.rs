#[derive(Debug, PartialEq, Eq)]
pub enum Error {
    NotEnoughPinsLeft,
    GameComplete,
}

#[derive(Debug, Copy, Clone)]
pub enum ScoreType {
    OpenFrame,
    Spare,
    Strike,
}

#[derive(Debug)]
pub struct Frame {
    throws: Vec<u16>,
    score_type: Option<ScoreType>,
}

impl Frame {
    pub fn new() -> Self {
        Frame {
            throws: Vec::new(),
            score_type: None,
        }
    }
}

#[derive(Debug)]
pub struct BowlingGame {
    frames: Vec<Frame>,
}

impl BowlingGame {
    pub fn new() -> Self {
        BowlingGame { frames: Vec::new() }
    }

    pub fn roll(&mut self, pins: u16) -> Result<(), Error> {
        if pins > 10 {
            return Err(Error::NotEnoughPinsLeft);
        }
        if self.is_game_done() {
            return Err(Error::GameComplete);
        }
        if self.frames.len() < 10 {
            // Create a new frame as necessary.
            if self.frames.is_empty() {
                self.frames.push(Frame::new());
            } else {
                let frame = self.frames.get(self.frames.len() - 1).unwrap();
                if (frame.throws.len() == 1 && frame.throws.iter().sum::<u16>() == 10)
                    || frame.throws.len() == 2
                {
                    self.frames.push(Frame::new());
                }
            }
        }

        let length = self.frames.len();
        let frame = self.frames.get_mut(length - 1).unwrap();
        frame.throws.push(pins);
        let score = frame.throws.iter().sum::<u16>();
        if length < 10 && score > 10 {
            return Err(Error::NotEnoughPinsLeft);
        }
        match frame.score_type {
            None => {
                if frame.throws.len() == 1 && score == 10 {
                    frame.score_type = Some(ScoreType::Strike);
                } else if frame.throws.len() == 2 && score == 10 {
                    frame.score_type = Some(ScoreType::Spare);
                } else if frame.throws.len() == 2 {
                    frame.score_type = Some(ScoreType::OpenFrame);
                }
            }
            Some(score_type) => match score_type {
                ScoreType::OpenFrame => {
                    if score > 10 {
                        return Err(Error::NotEnoughPinsLeft);
                    }
                }
                ScoreType::Spare => {
                    if score > 20 {
                        return Err(Error::NotEnoughPinsLeft);
                    }
                }
                ScoreType::Strike => {
                    if *frame.throws.get(1).unwrap() < 10
                        && frame.throws[1..].iter().sum::<u16>() > 10
                    {
                        return Err(Error::NotEnoughPinsLeft);
                    }
                }
            },
        }
        Ok(())
    }

    pub fn score(&self) -> Option<u16> {
        if !self.is_game_done() {
            return None;
        }
        let score = self
            .frames
            .iter()
            .flat_map(|f| f.throws.iter())
            .sum::<u16>();
        let bonus = self
            .frames
            .iter()
            .enumerate()
            .map(|(i, frame)| match frame.score_type.unwrap() {
                ScoreType::Spare => self.calculate_bonus(i + 1, 1),
                ScoreType::Strike => self.calculate_bonus(i + 1, 2),
                _ => 0,
            })
            .sum::<u16>();
        Some(score + bonus)
    }

    fn is_game_done(&self) -> bool {
        self.frames.len() == 10
            && self
                .frames
                .get(self.frames.len() - 1)
                .iter()
                .all(|f| f.score_type.is_some())
            && self
                .frames
                .get(self.frames.len() - 1)
                .iter()
                .flat_map(|frame| {
                    frame.score_type.map(|score_type| match score_type {
                        ScoreType::OpenFrame => frame.throws.len() == 2,
                        ScoreType::Spare | ScoreType::Strike => frame.throws.len() == 3,
                    })
                })
                .all(|b| b)
    }

    fn calculate_bonus(&self, i: usize, n: usize) -> u16 {
        *(&self.frames[i..]
            .iter()
            .flat_map(|f| f.throws.iter().copied())
            .take(n)
            .sum::<u16>())
    }
}
