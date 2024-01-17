use std::collections::{HashMap, HashSet};

pub fn solve(input: &str) -> Option<HashMap<char, u8>> {
    fn str_to_num(s: &str, map: &HashMap<char, u8>) -> Option<u64> {
        let mut string = String::new();
        for char in s.chars() {
            string.push_str(&map.get(&char).unwrap().to_string());
        }
        if string.starts_with("0") {
            None
        } else {
            Some(string.parse::<u64>().unwrap())
        }
    }

    fn solve(
        lhs: &Vec<&str>,
        rhs: &str,
        chars: &mut Vec<char>,
        map: &mut HashMap<char, u8>,
    ) -> bool {
        if chars.is_empty() {
            let lhs_num: Vec<Option<u64>> = lhs.iter().map(|s| str_to_num(*s, map)).collect();
            let rhs_num = str_to_num(rhs, map);
            if lhs_num.iter().any(|o| o.is_none()) || rhs_num.is_none() {
                return false;
            }
            return lhs_num.into_iter().map(|o| o.unwrap()).sum::<u64>() == rhs_num.unwrap();
        }
        let char = &chars.pop().unwrap();
        for num in 0..10 {
            if map.contains_key(char) {
                continue;
            }
            if map.values().any(|n| *n == num) {
                continue;
            }
            map.insert(*char, num);
            let found = solve(lhs, rhs, chars, map);
            if found {
                return true;
            }
            map.remove(char);
        }
        chars.push(*char);
        false
    }

    let mut split = input.split(" == ");
    let lhs: Vec<&str> = split.next().unwrap().split(" + ").collect();
    let rhs = split.next().unwrap();
    let mut chars: Vec<char> = input
        .chars()
        .filter(|c| !" +=".contains(*c))
        .collect::<HashSet<char>>()
        .into_iter()
        .collect();
    let mut map: HashMap<char, u8> = HashMap::new();
    if solve(&lhs, rhs, &mut chars, &mut map) {
        Some(map)
    } else {
        None
    }
}
