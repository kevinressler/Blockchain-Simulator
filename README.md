# RessCoin 🪙

A simple blockchain simulation written in Java. This project demonstrates the core principles behind cryptocurrencies including block creation, mining with adjustable difficulty, SHA-256 hashing, and a reward-based incentive system for miners.

## 🚀 Features

- 🔐 Blocks hashed using **SHA-256**
- 🧩 Adjustable **mining difficulty**
- ⛏️ **Proof-of-Work** mining system
- 🧑‍💻 Miner race with dynamic **reward system**
- 👥 Built-in **user wallet** simulation with balances
- 📦 Genesis block initialization

## 🛠️ How It Works

- Blocks contain a sender, receiver, transaction amount, and a hash.
- Miners compete to find a valid hash by brute-force (simulated).
- The first miner to find a valid hash earns a reward in **RessCoin**.
- Users can send coins if they have sufficient balance.

## 📂 Structure

- `BlockChain.java` — manages the chain and transactions
- `Block.java` — represents each block and handles mining logic
- `User.java` — wallet for each participant
- `Miner.java` — miner logic and rewards

## 💡 Usage

1. Clone the repo:
   ```bash
   git clone https://github.com/yourusername/RessCoin.git
