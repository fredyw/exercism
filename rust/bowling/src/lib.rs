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
        // Tenth frame is a special case.
        if length == 10 {
            if let Some(ref score_type) = frame.score_type {
                match score_type {
                    ScoreType::OpenFrame => {
                        return Err(Error::GameComplete);
                    }
                    ScoreType::Spare => {
                        if frame.throws.len() + 1 > 3 {
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
                    if score > 30 {
                        return Err(Error::NotEnoughPinsLeft);
                    }
                }
            },
        }
        Ok(())
    }

    pub fn score(&self) -> Option<u16> {
        if self.frames.len() == 10 {
            if let Some(frame) = self.frames.get(self.frames.len() - 1) {
                match frame.score_type {
                    None => return None,
                    Some(score_type) => match score_type {
                        ScoreType::OpenFrame => {
                            if frame.throws.len() < 2 {
                                return None;
                            }
                        }
                        ScoreType::Spare => {
                            if frame.throws.len() < 3 {
                                return None;
                            }
                        }
                        ScoreType::Strike => {
                            if frame.throws.len() < 3 {
                                return None;
                            }
                        }
                    },
                }
            }
            Some(0)
        } else {
            None
        }
    }
}
