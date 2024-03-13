#[derive(Debug, PartialEq, Eq)]
pub enum Error {
    NotEnoughPinsLeft,
    GameComplete,
}

#[derive(Debug)]
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
        if self.frames.len() < 9 {
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
        // Tenth frame is a special case.
        if length == 10 {
            if let Some(ref score_type) = frame.score_type {
                match score_type {
                    ScoreType::OpenFrame => {
                        return Err(Error::GameComplete);
                    }
                    ScoreType::Spare => {
                        if frame.throws.len() + 1 > 2 {
                            return Err(Error::GameComplete);
                        }
                    }
                    ScoreType::Strike => {
                        if frame.throws.len() + 1 > 3 {
                            return Err(Error::GameComplete);
                        }
                    }
                }
            }
        }
        let score = frame.throws.iter().sum::<u16>();
        if score + pins > 10 {
            return Err(Error::NotEnoughPinsLeft);
        }
        frame.throws.push(pins);
        if frame.throws.len() == 1 && score == 10 {
            frame.score_type = Some(ScoreType::Strike);
        } else if frame.throws.len() == 2 && score == 10 {
            frame.score_type = Some(ScoreType::Spare);
        } else if frame.throws.len() == 2 {
            frame.score_type = Some(ScoreType::OpenFrame);
        }
        Ok(())
    }

    pub fn score(&self) -> Option<u16> {
        None
    }
}
