use std::collections::HashMap;

pub struct CodonsInfo<'a> {
    map: HashMap<&'a str, &'a str>,
}

impl<'a> CodonsInfo<'a> {
    pub fn new() -> Self {
        Self {
            map: HashMap::new(),
        }
    }

    pub fn name_for(&self, codon: &str) -> Option<&'a str> {
        self.map.get(codon).copied()
    }

    pub fn of_rna(&self, rna: &str) -> Option<Vec<&'a str>> {
        let mut proteins = vec![];
        let chars: Vec<char> = rna.chars().collect::<Vec<char>>();
        for chunk in chars.chunks(3) {
            let codon = chunk.iter().collect::<String>();
            match self.map.get(&*codon) {
                None => return None,
                Some(&protein) => {
                    if protein == "stop codon" {
                        break;
                    }
                    proteins.push(protein);
                }
            }
        }
        Some(proteins)
    }
}

pub fn parse<'a>(pairs: Vec<(&'a str, &'a str)>) -> CodonsInfo<'a> {
    let mut codons_info = CodonsInfo::new();
    pairs.iter().for_each(|(codon, protein)| {
        codons_info.map.insert(codon, protein);
    });
    codons_info
}
