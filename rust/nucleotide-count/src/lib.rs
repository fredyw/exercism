use std::collections::HashMap;

pub fn count(nucleotide: char, dna: &str) -> Result<usize, char> {
    if !is_valid(nucleotide) {
        return Err(nucleotide);
    }
    match dna.chars().find(|&c| !is_valid(c)) {
        Some(c) => Err(c),
        None => Ok(dna.chars().filter(|&c| c == nucleotide).count()),
    }
}

pub fn nucleotide_counts(dna: &str) -> Result<HashMap<char, usize>, char> {
    match dna.chars().find(|&c| !is_valid(c)) {
        Some(c) => Err(c),
        None => {
            let mut map: HashMap<char, usize> = HashMap::new();
            map.insert('A', 0);
            map.insert('C', 0);
            map.insert('G', 0);
            map.insert('T', 0);
            for c in dna.chars() {
                *map.entry(c).or_insert(0) += 1;
            }
            Ok(map)
        }
    }
}

fn is_valid(nucleotide: char) -> bool {
    nucleotide == 'A' || nucleotide == 'C' || nucleotide == 'G' || nucleotide == 'T'
}
