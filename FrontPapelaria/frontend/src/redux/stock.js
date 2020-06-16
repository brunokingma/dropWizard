import * as ActionTypes from './actionsType';

export const Stock = (state = { errMess: null, stocks:[]}, action) => {
  switch (action.type) {
    case ActionTypes.ADD_STOCKS:
      return {...state, errMess: null, stocks: action.payload};

    case ActionTypes.STOCKS_FAILED:
      return {...state, errMess: action.payload};

    case ActionTypes.ADD_STOCK:
        var stock = action.payload;
        return { ...state, stocks: state.stocks.concat(stock)};

    default:
      return state;
  }
};