import './App.css';
import React, {Component} from "react";
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom'
import Manufacturers from '../Manufacturers/manufacturers';
import Categories from '../Categories/categories';
import Products from '../Products/ProductList/products';
import Header from '../Header/header';
import ProductAdd from '../Products/ProductAdd/productAdd';
import EShopService from "../../repository/eshopRepository";
import ProductEdit from "../Products/ProductEdit/productEdit";

class App extends Component {

  constructor(props) {
    super(props);
    this.state = {
      authors: [],
      books: [],
      categories: [],
      selectedBook: {}
    }
  }

  render() {
    return (
        <Router>
          <Header/>
          <main>
            <div className="container">
              <Route path={"/authors"} exact render={() =>
                  <Manufacturers authors={this.state.authors}/>}/>

              <Route path={"/categories"} exact render={() =>
                  <Categories categories={this.state.categories}/>}/>

              <Route path={"/books/add"} exact render={() =>
                  <ProductAdd categories={this.state.categories}
                              authors={this.state.authors}
                              onAddBook={this.addBook}/>}/>

              <Route path={"/books/edit"} exact render={() =>
                  <ProductEdit categories={this.state.categories}
                               authors={this.state.authors}
                               onEditBook={this.editBook}
                               book={this.state.selectedBook}/>}/>

              <Route path={"/books"} exact render={() =>
                  <Products books={this.state.books}
                            onDelete={this.deleteBook}
                            onEdit={this.getBook}/>}/>

              <Redirect to={"/books"}/>
            </div>
          </main>
        </Router>
    );
  }

  componentDidMount() {
    this.loadAuthors();
    this.loadCategories();
    this.loadBooks();
  }

  loadAuthors = () => {
    EShopService.fetchAuthors()
        .then((data) => {
          this.setState({
            authors: data.data
          })
        });
  }

  loadBooks = () => {
    EShopService.fetchBooks()
        .then((data) => {
          this.setState({
            books: data.data
          })
        });
  }

  loadCategories = () => {
    EShopService.fetchCategories()
        .then((data) => {
          this.setState({
            categories: data.data
          })
        });
  }

  deleteBook = (id) => {
    EShopService.deleteBook(id)
        .then(() => {
          this.loadBooks();
        });
  }

  addBook = (name, category, authorId, availableCopies) => {
    EShopService.addBook(name, category, authorId, availableCopies)
        .then(() => {
          this.loadBooks();
        });
  }

  getBook = (id) => {
    EShopService.getBook(id)
        .then((data) => {
          this.setState({
            selectedBook: data.data
          })
        })
  }

  editBook = (id, name, price, quantity, category, manufacturer) => {
    EShopService.editBook(id, name, price, quantity, category, manufacturer)
        .then(() => {
          this.loadBooks();
        });
  }
}

export default App;
