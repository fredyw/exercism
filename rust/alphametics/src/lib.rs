use std::collections::{HashMap, HashSet};

pub fn solve(input: &str) -> Option<HashMap<char, u8>> {
    fn to_num(strings: &Vec<&str>, map: &HashMap<char, u8>) -> u64 {
        let mut sum = 0;
        for s in strings.iter() {
            let mut num = 0;
            for char in s.chars() {
                let digit = *map.get(&char).unwrap() as u64;
                num += digit;
                num *= 10;
            }
            sum += num;
        }
        sum
    }

    fn solve(
        lhs: &Vec<&str>,
        rhs: &str,
        leading: &Vec<u8>,
        chars: &mut Vec<char>,
        nums: &mut [bool; 10],
        map: &mut HashMap<char, u8>,
    ) -> bool {
        if chars.is_empty() {
            let lhs_num = to_num(lhs, map);
            let rhs_num = to_num(&vec![rhs], map);
            return lhs_num == rhs_num;
        }
        let char = chars.pop().unwrap();
        for num in 0..10 {
            if nums[num as usize] || (num == 0 && leading.contains(&(char as u8))) {
                continue;
            }
            nums[num as usize] = true;
            map.insert(char, num);
            let found = solve(lhs, rhs, leading, chars, nums, map);
            if found {
                return true;
            }
            nums[num as usize] = false;
        }
        chars.push(char);
        false
    }

    let mut split = input.split(" == ");
    let lhs: Vec<&str> = split.next().unwrap().split(" + ").collect();
    let rhs = split.next().unwrap();
    let leading: Vec<u8> = input
        .split(|c| c == ' ' || c == '+' || c == '=')
        .filter_map(|w| w.bytes().nth(0))
        .collect::<HashSet<_>>()
        .into_iter()
        .collect();
    let mut chars: Vec<char> = input
        .chars()
        .filter(|c| !" +=".contains(*c))
        .collect::<HashSet<char>>()
        .into_iter()
        .collect();
    let mut map: HashMap<char, u8> = HashMap::new();
    let mut nums = [false; 10];
    if solve(&lhs, rhs, &leading, &mut chars, &mut nums, &mut map) {
        Some(map)
    } else {
        None
    }
}
