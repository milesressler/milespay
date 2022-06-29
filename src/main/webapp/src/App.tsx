import React from 'react';
import logo from './logo.svg';
import './App.css';
import Button from 'react-bootstrap/Button';
import {Form} from "react-bootstrap";

interface Card {
  id?: string
  name?: string
  balanceCents: number
}

interface Transaction {
  merchantName?: string
  amount?: string
  status?: string
}

function App() {
  const [loading, setLoading] = React.useState(false);
  const [token, setToken] = React.useState('');
  const [cardPage, setCardPage] = React.useState({content: [] as Card[]});
  const [transactions, setTransactions] = React.useState({content: [] as Transaction[]});
  const [selectedCard, setSelectedCard] = React.useState({} as Card|null);
  const [loginForm, setLoginForm] = React.useState({username: '', password: ''});
  const [cardPageOptions, setCardPageOptions] = React.useState({page: 1, pageSize: 25})

  const base_url = 'http://localhost:8080'

  function logout() {
    setToken('');
    setCardPage({content: []});
    setSelectedCard(null);
    setTransactions({content: []});
  }

  function getCards() {
    fetch(`${base_url}/card?page=${cardPageOptions.page}&pageSize=${cardPageOptions.pageSize}`,
        {headers: {'Authorization': `Bearer ${token}`}})
        .then(response => response.json())
        .then(data => setCardPage(data))
  }

  function getTransactionsForSelectedCard() {

    fetch(`${base_url}/card/${selectedCard?.id}/transaction`,
        {headers: {'Authorization': `Bearer ${token}`}})
        .then(response => response.json())
        .then(data => setTransactions(data))
  }

  React.useEffect(() => {
    if (!!token) {
      getCards();
    }
  }, [token])

  React.useEffect(() => {
    if (!!token) {
      getTransactionsForSelectedCard();
    }
  }, [selectedCard])


  function login() {
    const requestOptions = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ username: loginForm.username, password: loginForm.password})
    };
    setLoading(true);
    fetch(`${base_url}/authenticate`, requestOptions)
        .then(response => response.json())
        .then(data => { setToken( data.accessToken ); setLoading(false);  });
    setLoginForm({username: '', password: ''});
  }

  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
      <div>
        {!token &&
        <Form>
          <Form.Group className="mb-3" controlId="username">
            <Form.Label>Email address</Form.Label>
            <Form.Control type="email" placeholder="Enter email" value={loginForm.username} onChange={event => {
              setLoginForm({...loginForm, ...{
                  username : event.target.value
                }});
            }} />
            <Form.Text className="text-muted">
              We <strong>never</strong> log or save your email address.
            </Form.Text>
          </Form.Group>

          <Form.Group className="mb-3" controlId="password">
            <Form.Label>Password</Form.Label>
            <Form.Control type="password" placeholder="Password" value={loginForm.password} onChange={event => {
              setLoginForm({...loginForm, ...{
                  password : event.target.value
                }});
            }} />
          </Form.Group>
          <Button variant="primary" onClick={login}>
            Log in
          </Button>
        </Form>}
        {
            token && <div>
              {
                cardPage?.content.map(card =>
                    <div onClick={() => setSelectedCard(card)} style={{padding: '5px'}}>
                      <span style={{backgroundColor: selectedCard?.id === card.id ? '#A00' : 'none'}}>
                        {card.name} - {card.id} (${card.balanceCents/100})
                      </span>
                    </div>)
              }
              {
                transactions?.content.map(tx =>
                    <div >
                      <span >
                       {tx.status}: {tx.amount} at {tx.merchantName}
                      </span>
                    </div>)
              }
              <Button variant="primary" onClick={logout}>
                Log Out
              </Button>
            </div>

        }
      </div>

      </header>
    </div>
  );
}

export default App;
