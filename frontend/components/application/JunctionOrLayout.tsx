import {
  ApplicationNode,
  ApplicationQuestion,
} from "@/src/constants/application/type";
import { JunctionQuestion } from "./Junction.component";
import { ApplicationLayout } from "./Layout.component";
import { FC } from "react";

const checkQuestion = (node: any): node is ApplicationQuestion => {
  return "id" in node;
};

interface JunctinOrLayoutProps {
  node: ApplicationQuestion | ApplicationNode;
}

export const JunctinOrLayout: FC<JunctinOrLayoutProps> = ({ node }) => {
  return checkQuestion(node) ? (
    <ApplicationLayout applicationQuestion={node} />
  ) : (
    <JunctionQuestion applicationNodeData={node} />
  );
};
