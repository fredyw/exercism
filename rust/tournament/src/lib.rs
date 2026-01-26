use std::cmp::Ordering;
use std::collections::HashMap;

#[derive(Debug)]
struct Team {
    name: String,
    matches: u32,
    won: u32,
    drawn: u32,
    lost: u32,
    points: u32,
}

impl Team {
    fn new(name: &str) -> Team {
        Team {
            name: name.to_string(),
            matches: 0,
            won: 0,
            drawn: 0,
            lost: 0,
            points: 0,
        }
    }
}

pub fn tally(match_results: &str) -> String {
    let mut teams: HashMap<String, Team> = HashMap::new();
    for line in match_results.split('\n') {
        if line.is_empty() {
            continue;
        }
        let parts: Vec<&str> = line.split(';').collect();
        let team1 = teams
            .entry(parts[0].to_string())
            .or_insert(Team::new(parts[0]));
        team1.matches += 1;
        if parts[2] == "win" {
            team1.won += 1;
            team1.points += 3;
        } else if parts[2] == "draw" {
            team1.drawn += 1;
            team1.points += 1;
        } else {
            team1.lost += 1;
        }
        let team2 = teams
            .entry(parts[1].to_string())
            .or_insert(Team::new(parts[1]));
        team2.matches += 1;
        if parts[2] == "win" {
            team2.lost += 1;
        } else if parts[2] == "draw" {
            team2.drawn += 1;
            team2.points += 1;
        } else {
            team2.won += 1;
            team2.points += 3;
        }
    }
    let mut sorted_teams = teams.values().collect::<Vec<&Team>>();
    sorted_teams.sort_by(|team1, team2| {
        let cmp = team2.points.cmp(&team1.points);
        match cmp {
            Ordering::Equal => team1.name.cmp(&team2.name),
            _ => cmp,
        }
    });
    let header = format!(
        "{:<30} | {:>2} | {:>2} | {:>2} | {:>2} | {:>2}",
        "Team", "MP", "W", "D", "L", "P"
    );
    let body = sorted_teams
        .iter()
        .map(|team| {
            format!(
                "{:<30} | {:>2} | {:>2} | {:>2} | {:>2} | {:>2}",
                team.name, team.matches, team.won, team.drawn, team.lost, team.points
            )
        })
        .collect::<Vec<_>>()
        .join("\n");
    format!("{}\n{}", header, body).trim().to_owned()
}
