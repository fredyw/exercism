#[derive(Debug, PartialEq, Eq)]
pub enum Error {
    IncompleteNumber,
}

/// Convert a list of numbers to a stream of bytes encoded with variable length encoding.
pub fn to_bytes(values: &[u32]) -> Vec<u8> {
    let mut encoded = vec![];
    for val in values.iter().rev() {
        if *val == 0 {
            encoded.push(0);
        } else {
            let mut v = *val;
            let mut last = true;
            while v > 0 {
                if last {
                    encoded.push((v & 0b_0111_1111) as u8);
                } else {
                    encoded.push((v & 0b_0111_1111) as u8 | 0b_1000_0000);
                }
                v >>= 7;
                last = false;
            }
        }
    }
    encoded.reverse();
    encoded
}

/// Given a stream of bytes, extract all numbers which are encoded in there.
pub fn from_bytes(bytes: &[u8]) -> Result<Vec<u32>, Error> {
    let mut decoded = vec![];
    Ok(decoded)
}
