import { Injectable } from '@angular/core';
import { Product } from '../models/product.model';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private products: Product[] = [
    { id: 1, name: 'DELL - Laptop', image: 'dell.jpg', price: 20, category: 'Laptops' },
    { id: 2, name: 'HP - Laptop', image: 'hp.jpg', price: 52, category: 'Laptops' },
    { id: 3, name: 'ACER - Laptop', image: 'acer.jpg', price: 48, category: 'Laptops' },
    { id: 4, name: 'Lenovo - Laptop', image: 'lenovo.jpg', price: 250, category: 'Laptops' },
    { id: 5, name: 'Samsung Galaxy', image: 'samsung.jpg', price: 150, category: 'Mobiles' },
    { id: 6, name: 'iPhone 14', image: 'iphone.jpg', price: 800, category: 'Mobiles' },
  ];

  getProducts(): Product[] {
    return this.products;
  }
}
