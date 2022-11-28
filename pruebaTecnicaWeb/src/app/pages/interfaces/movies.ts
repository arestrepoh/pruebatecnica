
import { Gender } from "./gender";
import { Type } from "./type";
/**
 * Interface de la estructura de datos Movies
 */
export interface Movies {
    id: number;
    name: string;
    gender: Gender;
    type: Type;
    numberviews: number;
    score: number;
    view: boolean;
}
