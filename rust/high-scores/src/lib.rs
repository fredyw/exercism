#[derive(Debug)]
pub struct HighScores<'a> {
    scores: &'a [u32],
    best: Option<u32>,
    top_three: Vec<u32>,
}

impl<'a> HighScores<'a> {
    pub fn new(scores: &'a [u32]) -> Self {
        let mut v = scores.to_vec();
        v.sort_unstable_by(|a, b| b.cmp(a));
        v.truncate(3);
        HighScores {
            scores,
            best: v.first().copied(),
            top_three: v,
        }
    }

    pub fn scores(&self) -> &[u32] {
        self.scores
    }

    pub fn latest(&self) -> Option<u32> {
        self.scores.last().copied()
    }

    pub fn personal_best(&self) -> Option<u32> {
        self.best
    }

    pub fn personal_top_three(&self) -> Vec<u32> {
        self.top_three.clone()
    }
}
