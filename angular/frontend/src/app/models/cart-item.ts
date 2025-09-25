import { Product } from './product';
import { User } from './user';
export interface CartItem {
  id: number;
  cantidad: number;
  product?: Product;
  user?: User;
}