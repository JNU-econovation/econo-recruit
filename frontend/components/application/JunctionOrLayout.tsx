import {
  ApplicationNode,
  ApplicationQuestion,
} from "@/src/constants/application/type";
import { junctionQuestion } from "./Junction.component";
import { applicationLayout } from "./Layout.component";

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
