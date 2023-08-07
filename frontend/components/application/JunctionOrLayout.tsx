import {
  ApplicationNode,
  ApplicationQuestion,
} from "@/constants/application/type";
import { junctionQuestion } from "./ApplicationJunction.component";
import { applicationLayout } from "./ApplicationLayout.component";

const checkQuestion = (node: any): node is ApplicationQuestion => {
  return "id" in node;
};

export const junctinOrLayout = (
  node: ApplicationQuestion | ApplicationNode
) => {
  return checkQuestion(node)
    ? applicationLayout(node.direction, node)
    : junctionQuestion(node);
};
