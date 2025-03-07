#[derive(Debug, PartialEq, Eq)]
pub struct Dna {
    dna: String,
}

#[derive(Debug, PartialEq, Eq)]
pub struct Rna {
    rna: String,
}

impl Dna {
    pub fn new(dna: &str) -> Result<Dna, usize> {
        match dna
            .chars()
            .enumerate()
            .find(|(_, c)| *c != 'G' && *c != 'C' && *c != 'T' && *c != 'A')
        {
            Some((i, _)) => Err(i),
            None => Ok(Self { dna: dna.into() }),
        }
    }

    pub fn into_rna(self) -> Rna {
        Rna::new(
            &self
                .dna
                .chars()
                .map(|c| match c {
                    'G' => 'C',
                    'C' => 'G',
                    'T' => 'A',
                    _ => 'U',
                })
                .collect::<String>(),
        )
        .expect("Invalid RNA")
    }
}

impl Rna {
    pub fn new(rna: &str) -> Result<Rna, usize> {
        match rna
            .chars()
            .enumerate()
            .find(|(_, c)| *c != 'C' && *c != 'G' && *c != 'A' && *c != 'U')
        {
            Some((i, _)) => Err(i),
            None => Ok(Self { rna: rna.into() }),
        }
    }
}
