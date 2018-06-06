const cuid = require('cuid');
const express = require('express');
const bodyParser = require('body-parser')
const app = express();

/* Setup express middlewares */
app.use(bodyParser.json());
app.use(allowCorsMiddleware);

/* API */
app.get('/players', getPlayers);
app.get('/player/:id', getPlayer);
app.put('/player/:id', putPlayer);
app.post('/player', addPlayer);
app.delete('/player/:id', deletePlayer);

/* Start server */
app.listen(3000, () => console.log('app listening on port 3000.'))

/* The mock 'database' */
let players = [
  { id: 'cjeodaus60000poul1g030oia', name: 'Richard Garfield', bio: "Richard Channing Garfield (born June 26, 1963) is an American mathematician, inventor, and game designer. Garfield created Magic: The Gathering, which is considered to be the first modern collectible card game (CCG). Magicdebuted in 1993 and its success spawned many imitations. Garfield oversaw the successful growth of Magic and followed it with other game designs. Included in these are Netrunner, BattleTech(CCG), Vampire: The Eternal Struggle, Star Wars Trading Card Game, The Great Dalmuti, Artifact, and the board game RoboRally. He also created a variation of the card game Hearts called Complex Hearts. Garfield first became passionate about games when he played the roleplaying game Dungeons & Dragons, so he designed Magic decks to be customizable like roleplaying characters. Garfield and Magic are both in the Adventure Gaming Hall of Fame." },
  { id: 'cjeodaus60001poule3wjdz1p', name: 'Gabe Newell', bio: "Gabe Logan Newell (born November 3, 1962), often nicknamed Gaben, is an American computer programmer and businessman best known as the co-founder of the video game development and digital distribution company Valve Corporation. Born in Seattle, Newell attended Harvard University in the early 1980s, but dropped out shortly before graduating and soon went to work for the American technology company Microsoft, where he spent the next decade working as a producer for some of their early Windows operating systems. During his time at Microsoft, Newell, along with fellow co-worker Mike Harrington, were impressed by computer games that were being released in the mid-1990s, such as id Software's Doom and Quake. Fully convinced that video games were the future of entertainment, and intrigued by the prospect of having his own game development studio, Newell, along with Harrington, left Microsoft to co-found Valve in 1996, where he remains its president." },
]

/* IMPLEMENTATION DETAILS */

/* Return a list of all players
 * Example: localhost:3000/players
 */
function getPlayers(req, res) {
  return res.status(200).json(players).end();
}
/* Return a specific player based on id
 * Example: localhost:3000/player/cjeodaus60000poul1g030oia
 */
function getPlayer(req, res) {
  const id = req.params.id;
  const player = players.find(p => p.id == id);
  return player ? res.status(200).json(players).end() : res.status(404).end();
}
/* Add a new player to the list
 * Example: localhost:3000/player
 * Body: { "name": "Fresh Prince" } */
function addPlayer(req, res) {
  const name = req.body.name;
  if (!name) {
    return res.status(400).end();
  }
  const newPlayer = { id: cuid(), name };
  players = [...players, newPlayer];
  return res.status(201).json(newPlayer).end();
}
/* Delete a player from the list
 * Example: localhost:3000/player/cjeodaus60000poul1g030oia
 */
function deletePlayer(req, res) {
  const id = req.params.id;
  const removedPlayer = players.find(p => p.id == id);
  players = players.filter(p => p.id != id);
  return res.status(200).json(removedPlayer).end();
}
/* Edit an existing player in the list
 * Example: localhost:3000/player/cjeodaus60000poul1g030oia
 * Body: { "name": "Steve Urkle" }
 */
function putPlayer(req, res) {
  const id = req.params.id;
  const name = req.body.name;
  if (!name) {
    return res.status(400).end();
  }
  players = players.map(p => p.id == id ? { p, name } : p);
  return res.status(200).json(players.find(p => p.id == id)).end();
}

/* MISC */

/* Add CORS-headers to every request */
function allowCorsMiddleware(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
  next();
}